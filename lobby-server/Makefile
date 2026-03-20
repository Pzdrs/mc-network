.PHONY: run jar docker

jar:
	mvn clean package

docker:
	mvn compile -Pdev jib:dockerBuild

minikube:
	# dev profile to timestamp the image, sacrificing reproducibility for qol
	eval $$(minikube docker-env) && mvn compile -Pdev jib:dockerBuild