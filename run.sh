#!/bin/bash

echo -e "\033[1;32m"
echo "╔══════════════════════════════════════════╗"
echo "║           zTracker Installer             ║"
echo "║                zdeCodeR                  ║"
echo "╚══════════════════════════════════════════╝"
echo -e "\033[0m"

if ! command -v java &> /dev/null; then
    echo -e "\033[1;33m[+] Installing Java...\033[0m"
    pkg update && pkg install openjdk-17 -y
fi

if ! command -v wget &> /dev/null; then
    echo -e "\033[1;33m[+] Installing wget...\033[0m"
    pkg install wget -y
fi

if [ ! -f "json-20210307.jar" ]; then
    echo -e "\033[1;33m[+] Downloading JSON library...\033[0m"
    wget https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
fi

if [ ! -f "AzamTracker.java" ]; then
    echo -e "\033[1;31m[!] Error: AzamTracker.java not found!\033[0m"
    echo "Please make sure AzamTracker.java is in the current directory"
    exit 1
fi

echo -e "\033[1;33m[+] Compiling zTracker...\033[0m"
javac -cp .:json-20210307.jar AzamTracker.java

if [ $? -eq 0 ]; then
    echo -e "\033[1;32m[✓] Compilation successful!\033[0m"
else
    echo -e "\033[1;31m[!] Compilation failed!\033[0m"
    exit 1
fi


echo -e "\033[1;32m[+] Starting zTracker...\033[0m"
echo -e "\033[1;36m----------------------------------------\033[0m"
java -cp .:json-20210307.jar AzamTracker
