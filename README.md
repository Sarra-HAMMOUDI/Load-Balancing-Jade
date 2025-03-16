# Multi-Agent System for Load Balancing in Cloud Computing

## Project Overview
This project implements a **multi-agent system** designed to optimize load balancing and minimize latency in a cloud computing environment. The system dynamically assigns client requests (video, image, or text) to the appropriate cluster head and ensures that requests are processed by the **underloaded server**. The system is implemented using the **JADE platform** and demonstrates significant improvements in execution and response times compared to traditional architectures.

## Key Features
- **Dynamic Request Assignment**: Analyzes request types (video, image, or text) and assigns them to the appropriate cluster head.
- **Load Balancing**: Ensures requests are processed by the underloaded server based on real-time CPU, storage, and database load information.
- **Multi-Agent Collaboration**: Agents work collaboratively to monitor server states, update directories, and handle database changes.
- **Fault Tolerance**: Monitors server health and alerts the system administrator in case of failures.

## Project Structure
All Java files are located in the `src` directory. The project is organized as follows:

├── src/
│   ├── PrincipalAgent.java
│   ├── AgentVideoSupervisor.java
│   ├── AgentVideoAnnuaire.java
│   ├── AgentImageSupervisor.java
│   ├── AgentImageAnnuaire.java
│   ├── AgentTextSupervisor.java
│   ├── AgentTextAnnuaire.java
│   ├── AgentMonitor.java
│   ├── AgentSurveillance.java
│   ├── AgentSupervisor.java
│   ├── Server1.java
│   ├── Server2.java
│   ├── Server3.java
│   └── Main.java
├── docs/
│   ├── deployment_diagram.png
│   ├── sequence_diagram.png
│   └── project_description.pdf
├── README.md



## Diagrams
### Deployment Diagram
The **Deployment Diagram** illustrates the physical deployment of the system components across servers and clusters. It shows how the **Resource Manager**, **Cluster Heads**, and **Servers** interact to ensure efficient load balancing.

![Deployment Diagram](docs/deployment_diagram.png)

### Sequence Diagram
The **Sequence Diagram** depicts the sequence of interactions between the system components during a client request. It highlights the dynamic assignment of requests to the underloaded server.

![Sequence Diagram](docs/sequence_diagram.png)

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or later.
- JADE platform for multi-agent system development.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git

2. Navigate to the project directory:

cd your-repo-name

3. Build the project using Maven:

mvn clean install


Running the Project

1. Start the JADE platform:
java -cp target/your-repo-name.jar jade.Boot -gui 

2. Run the main class to start the system:
java -cp target/your-repo-name.jar Main


3. Start the JADE Platform

1. Compile your Java classes:
javac -cp jade.jar:. *.java

Replace jade.jar with the path to your JADE library.

2.Start the JADE platform and run your agent:
java -cp jade.jar:. jade.Boot -gui -agents myAgent:MyAgent
