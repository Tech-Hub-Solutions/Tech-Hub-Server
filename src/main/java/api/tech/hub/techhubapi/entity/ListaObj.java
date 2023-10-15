package api.tech.hub.techhubapi.entity;

public class ListaObj<T> {
    private T vetor[];

    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (this.vetor.length == nroElem) {
            System.out.println("Lista está cheia!");
        } else {
            this.vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elementoBuscado) {

        for (int i = 0; i < nroElem; i++) {
            if (this.vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return false;
        }

        for (int i = indice; i < nroElem - 1; i++) {
            this.vetor[i] = this.vetor[i + 1];
        }
        nroElem--;
        return true;
    }


    public boolean removeElemento(T elementoARemover) {
        int indice = busca(elementoARemover);
        if (indice == -1) {
            return false;
        }
        removePeloIndice(indice);
        return true;
    }


    public int getTamanho() {
        return nroElem;
    }


    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        } else {
            return this.vetor[indice];
        }
    }

    public void limpa() {
        this.nroElem = 0;
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("Exibindo relatório");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public T[] getVetor() {
        return this.vetor;
    }
}


