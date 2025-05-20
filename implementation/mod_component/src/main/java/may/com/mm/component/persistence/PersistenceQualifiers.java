package may.com.mm.component.persistence;

public class PersistenceQualifiers {

    public static class Common {

        public static final String DATA_SOURCE = "commonDataSource";
        public static final String ENTITY_MANAGER_FACTORY = "commonEntityManagerFactory";
        public static final String TRANSACTION_MANAGER = "commonTransactionManager";
    }

    public static class Operation {

        public static final String DATA_SOURCE = "operationDataSource";
        public static final String ENTITY_MANAGER_FACTORY = "operationEntityManagerFactory";
        public static final String TRANSACTION_MANAGER = "operationTransactionManager";
    }

    public static class Overlap {

        public static final String DATA_SOURCE = "overlapDataSource";
        public static final String ENTITY_MANAGER_FACTORY = "overlapEntityManagerFactory";
        public static final String TRANSACTION_MANAGER = "overlapTransactionManager";
    }
}
