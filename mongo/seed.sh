#!/bin/bash

echo "Creating db if not exists..."
mongo --eval "db.getSiblingDB('review-service')" \
  --host review-service-mongodb \
  -u root -p root --authenticationDatabase admin

echo "Seeding data..."

mongoimport \
  --host review-service-mongodb \
  --db review-service --collection reviews \
  --type json --file import/seed.json --jsonArray \
  -u root -p root --authenticationDatabase admin
