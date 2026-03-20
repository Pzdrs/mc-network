.PHONY: run jar docker

run:
	mvn compile exec:java

jar:
	mvn clean package

docker:
	mvn compile jib:dockerBuild