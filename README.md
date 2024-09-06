# Read Me First
Projektet tog cirka 6 timmar att slutföra, vilket översteg den ursprungliga uppskattningen på 4 timmar. 
Detta berodde på implementeringen av både backend (med Spring Boot) och frontend (med Angular). 

Jag har lagt till en Dockerfile i projektets repository för att lösa problemet som uppstår på grund av skillnader mellan maskinmiljöer.
För att köra projektet, vänligen se till att Docker är installerat och körs på ditt system. Du behöver installera Git Bash om du inte redan har gjort det. 

Navigera till projektets rotmapp.

Dubbelklicka på filen start.sh (detta öppnar ett bash-fönster som kör docker build/run-kommandot.
Bygg Docker-image med följande kommando:docker build -t stugbokningssystem .
och sedan, starta Docker-containern med:docker run -p 9090:9090 stugbokningssystem
).

Efter att applikationen har startat kan den nås på http://localhost:9090.
För att kollar alla bokningar som administratör, använd ett av följande användar-ID:n: 2408123 eller 2408123.
