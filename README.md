# test_spring_batch

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

# execute a terminal mongosh
```shell
docker exec -it mongodb mongosh
```

# starting and ending the service Mongo Docker
```shell
docker start mongodb
docker stop mongodb
```

## create collection MongoDB
```shell
db.createCollection('AfOdsMongo')
```