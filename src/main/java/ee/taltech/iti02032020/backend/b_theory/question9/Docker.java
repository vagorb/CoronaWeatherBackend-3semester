package ee.taltech.iti02032020.backend.b_theory.question9;

public class Docker {

    //todo A
    // What is a server?
    // Answer: Computer hardware or software that accepts and responds to requests made by other programs or users.

    //todo B
    // What is the difference between build server and production server?
    // Answer: Build server is only used by developers for testing, debugging and etc. This should survive often updating and refreshing of the server.
    // Production server is used by clients. It should be almost always live with working code. It is refreshed only, when a new tested build is deployed.

    //todo C
    // What is docker?
    // Answer: Software platform that virtualize the operating system of the computer to make process of building,
    // running, managing and distributing applications easier, by using containers with packaged necessary parts.

    //todo D
    // Name and explain docker container benefits over virtual machine setup (java jar as system process and installed nginx)
    // 1 Docker containers typically start faster than virtual machines, because machines need to emulate hardware, but containers run directly on servers.
    // 2 Containers require less things to be installed in order to run. They share many of their resources with the host system and they
    // consume less RAM and CPU time. Also it helps to reuse code.

    //todo E
    // Name and explain docker container drawback over virtual machine setup (java jar as system process and installed nginx)
    // 1 Virtual machines are more isolated from each other than are Docker containers, because virtual machines don’t directly share any resources with the host system.

    //todo F
    // Name and describe tools for docker architecture
    // 1 Kubernetes - automates management, placement, scaling and routing of containers.
    // 2 Docker compose - used to run multi-container Docker applications.
    // 3 Docker hub - helps to visualize containers.

    //todo G
    // Name and explain why are companies slow in moving existing systems to docker architecture (do not explain why docker is bad)
    // 1 It could be hard to split existing big system for smaller parts and because of that use of Docker will not gain much value.
    // 2 Moving existing systems to docker architecture is time consuming and could not bring profit.
    // Systems are working fine so there is no particular reason to do something with them and spend valuable time.
}
