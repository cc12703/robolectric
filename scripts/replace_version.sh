#!/bin/bash

find '../' -name 'pom.xml' -exec sed -i '' 's/$1/$2/g' {} \;

