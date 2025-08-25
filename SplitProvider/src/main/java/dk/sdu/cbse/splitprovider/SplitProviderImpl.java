package dk.sdu.cbse.splitprovider;

import dk.sdu.cbse.common.services.SplitProvider;

public class SplitProviderImpl implements SplitProvider {
    @Override
    public String helloProvider() {
        return "Hello from FIRST provider!";
    }
}