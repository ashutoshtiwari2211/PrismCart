# backend (Day 1, Java 21, Spring Boot 3.3.2)

## Prereqs
- JDK 21
- Docker (optional)
- (Optional) Local Gradle to bootstrap the wrapper

## First-time setup
If `gradle/wrapper/gradle-wrapper.jar` is missing, bootstrap the wrapper once:
- Windows (PowerShell):
  ```powershell
  gradle wrapper --gradle-version 8.7 --distribution-type=bin
  ```
- macOS/Linux:
  ```bash
  gradle wrapper --gradle-version 8.7 --distribution-type=bin
  chmod +x gradlew
  ```

## Build & Run (after wrapper exists)
```bash
./gradlew :auth-service:bootRun
```
Windows PowerShell:
```powershell
.\gradlew :auth-service:bootRun
```

## Test in browser
- Swagger UI: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/actuator/health
- Hello: http://localhost:8080/api/hello


marketplacex/
  backend/
    Commons/ 
      common-model/
      common-security/
      common-utils/
    Services/
      auth-service/
      user-service/
      catalog-service/
      inventory-service/
      cart-service/
      order-service/
      payment-service/
      notification-service/
      admin-bff/
    platform/
      gateway/
      config-server/
      service-discovery/
    Deploy/
      docker/
      k8s/
  docs/
  ui/   # optional React BFF
