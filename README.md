# test_spring_batch

# PostgreSQL

## start database
```shell
./starting.sh
```

## stop database
```shell
./stopping.sh
```

## enter database
```shell
docker exec -it spring_batch_db psql -U user -d db
```
# Mongo

## starting and ending the service Mongo Docker
```shell
docker start mongodb
docker stop mongodb
```

## execute a terminal mongosh
```shell
docker exec -it mongodb mongosh
```

## create collection MongoDB
```shell
db.createCollection('AfOds')
```

## run image sftp
```shell
docker run -p 22:22 \
-v /Users/rrodriguez/git/github/upload:/home/user/upload \
-d atmoz/sftp:latest \
user:password:1001
```