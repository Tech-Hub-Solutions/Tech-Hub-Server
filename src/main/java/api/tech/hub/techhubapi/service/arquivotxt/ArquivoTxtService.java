package api.tech.hub.techhubapi.service.arquivotxt;

import api.tech.hub.techhubapi.entity.perfil.flag.Flag;
import api.tech.hub.techhubapi.repository.FlagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArquivoTxtService {
    private Path diretorioBase = Path.of(System.getProperty("user.dir"));
    private final FlagRepository flagRepository;
    public void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Bloco try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro ao abrir o arquivo");
        }

        // Bloco try-catch para gravar o registro e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            erro.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro ao gravar o arquivo");
        }
    }

    public Resource exportarTxt() {
        List<Flag> lista = this.flagRepository.findAll();

        int contaRegDadosGravados = 0;
        String nomeArq = "Flags.txt";
        // Monta o registro de header
        String header = "00FLAG";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados (registros de corpo)
        for (Flag f : lista) {
            String corpo = "02";
            corpo += String.format("%-50.50s", f.getNome());
            corpo += String.format("%-50.50s", f.getArea());
            corpo += String.format("%-50.50s", f.getCategoria());
            // Grava o registro de corpo
            gravaRegistro(corpo, nomeArq);
            // Contabiliza a quantidade de reg de dados gravados
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDadosGravados);

        gravaRegistro(trailer, nomeArq);

        try {
            Path path = this.diretorioBase;
            Path filePath = Paths.get(path.toString(), "Flags.txt");
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Erro ao ler o arquivo");
        }
    }

    public void importarArquivoTxt(MultipartFile arquivo){
        if (arquivo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        File destino = new File("Flags.txt");

        try {
            arquivo.transferTo(destino);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
        }

        salvarConteudoTxt();
    }

    public void salvarConteudoTxt() {
        String nomeArq = "Flags.txt";
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, area, categoria;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;

        // Cria uma listaLida para receber os objetos Alunos com os dados lidos do arquivo
        List<Flag> listaLida = new ArrayList<>();

        // Bloco try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Erro ao abrir a leitura");
        }

        // Bloco try-catch para ler o arquivo
        try {
            // Le a primeira linha do arquivo
            registro = entrada.readLine();

            while (registro != null) {

                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Inicio do registro do tipo Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                    System.out.println("Data e hora de gravação do arquivo: " + registro.substring(6, 25));
                    System.out.println("Versão do documento: " + registro.substring(25, 27));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("Inicio do registro do tipo trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 7));
                    if (qtdRegDadosGravados == contaRegDadosLidos) {
                        System.out.println("Quantidade de reg de dados gravados é compatível com a " +
                                "quantidade de reg de dados lidos");
                    }
                    else {
                        System.out.println("Quantidade de reg de dados gravados é incompatível com a " +
                                "quantidade de reg de dados lidos");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("Inicio do registro de corpo");
                    nome = registro.substring(2, 52).trim();
                    area = registro.substring(52, 102).trim();
                    categoria = registro.substring(102, 152).trim();
                    // Contabiliza que leu mais um reg de dados
                    contaRegDadosLidos++;

                    // Criar um objeto Aluno com os dados lidos desse registro
                    Flag f = new Flag();
                    f.setNome(nome);
                    f.setArea(area);
                    f.setCategoria(categoria);

                    // Para importar esse dado, podemos fazer
                    // repository.save(a);

                    // No nosso caso, como não estamos conectados a banco de dados,
                    // vamos add o objeto a a uma listaLida

                    if (!this.flagRepository.existsFlagByNomeIgnoreCase(f.getNome())) {
                        listaLida.add(f);
                    }

                }
                else {
                    System.out.println("Registro inválido!");
                    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Arquivo com problemas na composição");
                }

                // Le o proximo registro do arquivo
                registro = entrada.readLine();
            } // fim do while

            // Fecha o arquivo
            entrada.close();

        } // fim do try
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Arquivo com problemas de leitura");
        }

        // Aqui tb seria possível enviar toda a lista para o BD
        // repository.saveAll(listaLida);

        this.flagRepository.saveAll(listaLida);

        try {
            File arquivo = new File("Flags.txt");

            if (arquivo.delete()) {
                System.out.println("Arquivo excluído com sucesso.");
            } else {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Falha ao excluir o arquivo");
            }
        } catch (SecurityException e) {
            System.err.println("Erro de segurança ao excluir o arquivo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Sem autorização para excluir o arquivo");
        }
    }

    public String getContentType(Resource resource) {
        try {
            return Files.probeContentType(Paths.get(resource.getURI()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(422), "Erro ao determinar o tipo de arquivo");
        }
    }
}
