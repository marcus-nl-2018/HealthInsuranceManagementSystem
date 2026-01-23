package com.health.insurance.DAOImpl;

import com.health.insurance.DAO.InsuredPersonDAO;
import com.health.insurance.beans.InsuredPerson;
import com.health.insurance.exception.DataAccessException;
import com.health.insurance.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InsuredPersonDAOImpl implements InsuredPersonDAO {
    @Override
    public boolean saveInsuredPerson(InsuredPerson insuredPerson) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.save(insuredPerson);
            hibernateTransaction.commit();
            hibernateSession.close();
            return true;
        } catch(Exception e) {
            throw new DataAccessException(
                    "Error saving Insurance Person",
                    e
            );
        }
    }

    @Override
    public void updateInsuredPerson(InsuredPerson insuredPerson) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.update(insuredPerson);
            hibernateTransaction.commit();
            hibernateSession.close();
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error updating Insurance Person",
                    e
            );
        }
    }

    @Override
    public InsuredPerson getInsuredPerson(int insuredPersonId) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            InsuredPerson insuredPerson = hibernateSession.get(InsuredPerson.class,insuredPersonId);
            hibernateTransaction.commit();
            hibernateSession.close();
            return insuredPerson;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error getting Insurance Person",
                    e
            );
        }
    }

    @Override
    public List<InsuredPerson> getInsuredPersons() {
        try {
            Session session = FactoryProvider.getSessionFactory().openSession();
            Query<InsuredPerson> query = session.createQuery("from InsuredPerson");
            List<InsuredPerson> listOfInsuredPersons = query.list();
            return listOfInsuredPersons;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error getting list of Insurance Person",
                    e
            );
        }
    }

    @Override
    public boolean removeInsuredPerson(InsuredPerson insuredPerson) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.delete(insuredPerson);
            hibernateTransaction.commit();
            hibernateSession.close();
            return true;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error removing Insurance Person",
                    e
            );
        }
    }
}
