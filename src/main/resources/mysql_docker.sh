docker pull mysql/mysql-server
docker run --name mysql_docker --restart=always -d -p 3306:3306 mysql/mysql-server

# check logs for the root password
docker logs mysql1 2>&1 | grep GENERATED

# bash into the container
docker exec -it mysql1 mysql -uroot -p

# connect to 
ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
