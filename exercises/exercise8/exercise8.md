1. -||-
Docker 
- Containers share the host OS kernel.
- Since containers don’t boot a full OS, containers are lightweight and start quickly.
- Containers are highly portable but depend on the host kernel.
- Containers are less isolated but more efficient for DevOps workflows.
(A kernel exploit could potentially affect all containers on a host.)
VM
- VMs require a full OS for each instance.
- VMs consume more CPU, RAM, and storage.
- VMs are bulkier but can run different OS types.
- VMs provide stronger isolation (better for untrusted workloads).

2. -||-
c. 

Continuous Integration (CI) is a DevOps practice where developers merge code changes into a shared repository multiple times a day.
Each integration triggers automated builds and tests to catch bugs early and ensure code stability.
It does not automatically deploy to production (that’s Continuous Delivery/Deployment).
While Docker containers (option d) can be part of CI, they’re not its main purpose.


3.
docker build -t website-monitor . 
Build image 
docker run -it --rm website-monitor
The flags:

-it allows interactive terminal access

--rm removes the container after it exits

4. b) Jenkins
Jenkins – A widely used open-source automation server for orchestrating CI/CD pipelines. It allows developers to automate building, testing, and deploying applications. Jenkins supports plugins for integrating with various tools and cloud platforms.

Kubernetes – A container orchestration tool (not a CI/CD tool) that automates the deployment, scaling, and management of containerized applications. While it can be part of a CI/CD pipeline (e.g., deploying containers), it does not directly manage the pipeline itself.

Ansible – A configuration management and automation tool used for provisioning infrastructure, deploying applications, and managing configurations. While it can be integrated into CI/CD pipelines, it is not primarily a pipeline orchestrator.

Terraform – An Infrastructure as Code (IaC) tool used for provisioning and managing cloud infrastructure. Like Ansible, it can be part of CI/CD workflows (e.g., setting up environments) but is not a pipeline orchestrator.

5. 
Run tests in parallel across multiple workers/machines.
rioritize fast tests: Run quick unit tests first, then slower integration/E2E tests.
Cache dependencies (e.g., Maven/Gradle, npm, pip) to avoid re-downloading.
Reuse build artifacts from previous stages instead of rebuilding.
Use in-memory databases (e.g., H2 for Java, SQLite for lightweight tests).
Minimize test data (only generate what’s necessary).
Reuse database state instead of rebuilding it for every test.
