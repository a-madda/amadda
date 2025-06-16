package com.seungse.amadda.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HibernateIdGenrator implements IdentifierGenerator {

    private final GeneralIdGenerator generalIdGenerator = new GeneralIdGenerator();

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return generalIdGenerator.generate();
    }

}
