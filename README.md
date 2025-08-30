# Clone repository
git clone https://github.com/zdeCodeR/zTracker.git
cd zTracker

# Install dependencies
pkg update && pkg upgrade
pkg install openjdk-17 wget -y

# Download JSON library
wget https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar

# Compile
javac -cp .:json-20210307.jar AzamTracker.java

# Run
java -cp .:json-20210307.jar AzamTracker
