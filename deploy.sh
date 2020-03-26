#!/usr/bin/env bash

mvn clean package -P $1

echo 'Copy files...'