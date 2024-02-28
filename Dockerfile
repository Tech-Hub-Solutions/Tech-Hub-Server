# Stage 1: Build the application
FROM amazoncorretto:17-alpine3.15-jdk AS builder
RUN apk add maven=3.8.3-r0
WORKDIR /build

COPY . .

RUN mvn -B -e clean install -DskipTests=true

# Stage 2: Final image
FROM amazoncorretto:17-alpine3.15-jdk AS runner
WORKDIR /app

COPY --from=builder /build/target/*.jar ./app.jar

# COPY ARQUIVOS for the application
COPY --from=builder /build/arquivos ./arquivos

EXPOSE 8080

CMD [ "java", "-jar", "app.jar"]