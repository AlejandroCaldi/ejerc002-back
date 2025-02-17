#!/bin/bash

BASE_URL="http://localhost:8080/api/boligrafo"

echo "creando un nuevo boligrafo..."
CREATE_RESPONSE=$(curl -s -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"color":"azul", "escribe": true, " nombre":"Mi primera Bic"}')

echo ""

CREATE_RESPONSE=$(curl -s -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"color":"verde", "escribe": true, "nombre":"Mi nueva Watermark"}')

echo ""
echo ""

echo "Create Response: $CREATE_RESPONSE"
BOLIGRAFO_ID=$(echo "$CREATE_RESPONSE" | jq -r '.id')

echo ""
echo ""

echo "Leyendo boligrafo con ID: $BOLIGRAFO_ID"
echo ""
curl -s -X GET "$BASE_URL/$BOLIGRAFO_ID" -H "Content-Type: application/json"

echo ""
echo ""

echo "Listado de todos los boligrafos..."
echo ""
curl -s -X GET "$BASE_URL" -H "Content-Type: application/json"

echo ""
echo ""

echo "Update de boligrafo..."
curl -s -X PUT "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{"id":1, "color":"rojo", "marca":"Bic"}'

echo ""
echo ""

echo "Borrado de un boligrafo con ID: $BOLIGRAFO_ID"

echo ""


curl -s -X DELETE "$BASE_URL/$BOLIGRAFO_ID" -H "Content-Type: application/json"

echo "Test terminado."
