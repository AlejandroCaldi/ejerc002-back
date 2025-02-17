#!/bin/bash

BASE_URL="http://localhost:8080/api/boligrafo"

# Create a new boligrafo
echo "Creating a new boligrafo..."
CREATE_RESPONSE=$(curl -s -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"color":"azul", "escribe": true, "marca":"Bic"}')

echo "Create Response: $CREATE_RESPONSE"
BOLIGRAFO_ID=$(echo "$CREATE_RESPONSE" | jq -r '.id')

# Read a boligrafo by ID
echo "Reading boligrafo with ID: $BOLIGRAFO_ID"
curl -s -X GET "$BASE_URL/$BOLIGRAFO_ID" -H "Content-Type: application/json"

# List all boligrafos
echo "Listing all boligrafos..."
curl -s -X GET "$BASE_URL" -H "Content-Type: application/json"

# Update the boligrafo
echo "Updating boligrafo..."
curl -s -X PUT "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"id":1, "color":"rojo", "marca":"Bic"}'

# Delete the boligrafo
echo " Deleting boligrafo with ID: $BOLIGRAFO_ID"
curl -s -X DELETE "$BASE_URL/$BOLIGRAFO_ID" -H "Content-Type: application/json"

echo "Test completed!"
