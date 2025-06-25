

SELECT CONCAT(p.first_name, ' ', p.last_name) AS full_name, s.status_name
FROM Player p JOIN Status s 
ON p.status_id = s.id
WHERE p.last_name LIKE 'U%'
ORDER BY full_name;

