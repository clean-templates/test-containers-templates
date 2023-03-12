package containers.postgres;

import org.testcontainers.containers.GenericContainer;

public class ConsulTestContainer extends GenericContainer<ConsulTestContainer> {

    private static final String CONSUL_IMAGE = "consul:1.11.3";
    private static ConsulTestContainer container;

    private ConsulTestContainer(){
        super(CONSUL_IMAGE);
        this.withExposedPorts(8500);
    }

    public static ConsulTestContainer getInstance(){
        if(container == null){
            container = new ConsulTestContainer();
        }
        return container;
    }



    @Override
    public void start() {
        super.start();
        System.setProperty("CONSUL_HOST", container.getHost());
        System.setProperty("CONSUL_PORT", container.getMappedPort(8500).toString());
    }

    @Override
    public void stop() {
        super.stop();
    }

}
