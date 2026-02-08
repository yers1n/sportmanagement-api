# Sport Management API (Assignment)

Учебный проект: REST API для управления спортсменами (Athlete) и видами спорта (Sport) на Spring Boot + JPA + PostgreSQL.

## Запуск
1) Открой проект в IntelliJ IDEA
2) Запусти класс `SportmanagementApiApplication`
3) Сервер стартует на `http://localhost:8080`
s
## Проверка в браузере (GET)
- http://localhost:8080/index.html
- http://localhost:8080/api/athletes
- http://localhost:8080/api/sports
- http://localhost:8080/api/athletes/sorted/age
- http://localhost:8080/api/athletes/championship/this-year
- http://localhost:8080/api/athletes/championship/next-year
- http://localhost:8080/api/athletes/sport/Judo

## Проверка через Terminal (curl) — один блок (копируй и запускай)

> Запусти приложение, потом вставь весь блок в Terminal.

```bash
echo "=== SPORTS: GET ALL ==="
curl -s http://localhost:8080/api/sports
echo -e "\n"

echo "=== SPORTS: ADD Boxing ==="
curl -s -X POST "http://localhost:8080/api/sports?name=Boxing"
echo -e "\n"

echo "=== SPORTS: GET ALL (after add) ==="
curl -s http://localhost:8080/api/sports
echo -e "\n"

echo "=== ATHLETES: GET ALL ==="
curl -s http://localhost:8080/api/athletes
echo -e "\n"

echo "=== ATHLETES: ADD TestAthlete (sportId=2) ==="
curl -s -X POST "http://localhost:8080/api/athletes?name=TestAthlete&age=17&ranking=1&sportId=2"
echo -e "\n"

echo "=== ATHLETES: GET ALL (after add) ==="
curl -s http://localhost:8080/api/athletes
echo -e "\n"

echo "=== ATHLETES: SORTED BY AGE ==="
curl -s http://localhost:8080/api/athletes/sorted/age
echo -e "\n"

echo "=== ATHLETES: FILTER BY SPORT (Judo) ==="
curl -s http://localhost:8080/api/athletes/sport/Judo
echo -e "\n"

echo "=== CHAMPIONSHIP: THIS YEAR (age >= 18) ==="
curl -s http://localhost:8080/api/athletes/championship/this-year
echo -e "\n"

echo "=== CHAMPIONSHIP: NEXT YEAR (age < 18) ==="
curl -s http://localhost:8080/api/athletes/championship/next-year
echo -e "\n"

echo "=== DELETE EXAMPLES (replace {id}) ==="
echo "Athlete delete: curl -X DELETE http://localhost:8080/api/athletes/{id}"
echo "Sport delete:   curl -X DELETE http://localhost:8080/api/sports/{id}"
