# Syscode
A két service-t külön külön projektbe raktam és külön porton is futnak. Ezzel biztosítva, hogy egymástól elkülönülve futtathatóak legyenek.
A main mappa tartalmazza a profile-service-t. Az address-service mappa pedig az address service-t.
## Profile-service
A profile-service indításkor létrehozok egy H2 adatbázist Liquibase segítségével. Adatbázisba a létrehozáskor beleraktam kettő darab példa Student-et. Így sokkal könnyebb volt a tesztelhetőség.
Minden kért funkcionalitást megvalósítottam. Ehhez definiáltam egy rest controller-t és ott a megfelelő endpointokat felvettem. Minden endpoint híváskor a kérést és a választ is loggolom a specifikációnak megfelelően. Létrehoztam továbbá egy service-t is. Ebbe került bele az email validáció és ez felelős, hogy biztosítsa a megfelelő adatokat a controllernek. Létrehoztam egy repository-t is. Ezt használtam a service osztályomba. Ennek segítségével értem el az adatokat az adatbázisból. Mivel nem volt semmilyen speciális lekérdezésre szükségem, így elegendő volt a repository által alapból biztosított metódusok. Természetesen létrehoztam még egy enitást is, ami a Student volt. A specifikációnak megfelelően készíttem el ezt az osztályt is. Készítettem még néhány unit tesztet és egy integrációs tesztet is. Ez a service a 8080 porton érhető el.
## Address-service
A specifikációban szereplő kérés miatt egy külön portra helyeztem ezt a service-t. Ez a 8081-es porton érhető el. Hozzá csak létrehoztam egy Address entitást. Létrehoztam neki is egy rest controllert. Mivel a specifiikáció azt mondta ne kapcsolódjon adatbázishoz, ezért a controllerben definiált get kérés hatására csak egy előre definiált address-t ad vissza. Itt is van loggolás természetesen. Ehhez a service-hez tartozik egy basic authentication. Ezt a Spring Security modul biztosítja. Mivel nem akartam a teszteléskor a konzolba mindig random generált jelszót használni, ezért a properties-ben definiáltam egy felhasználót user felhasználónévvel és 1234 jelszóval.
