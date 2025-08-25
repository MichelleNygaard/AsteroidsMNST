import dk.sdu.cbse.common.services.SplitProvider;
import dk.sdu.cbse.splitprovider.SplitProviderImpl;

module SplitProvider {
    requires Common;
    provides SplitProvider with SplitProviderImpl;
}