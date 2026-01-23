package com.health.insurance.DAOImpl;

import com.health.insurance.DAO.PhysicianDAO;
import com.health.insurance.beans.Physician;
import com.health.insurance.exception.DataAccessException;
import com.health.insurance.util.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PhysicianDAOImpl implements PhysicianDAO {
    @Override
    public boolean savePhysician(Physician physician) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.save(physician);
            hibernateTransaction.commit();
            hibernateSession.close();
            return true;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error saving Physician",
                    e
            );
        }
    }

    @Override
    public void updatePhysician(Physician physician) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.update(physician);
            hibernateTransaction.commit();
            hibernateSession.close();
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error updating Physician",
                    e
            );
        }
    }

    @Override
    public boolean removePhysician(Physician physician) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            hibernateSession.delete(physician);
            hibernateTransaction.commit();
            hibernateSession.close();
            return true;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error removing Physician",
                    e
            );
        }
    }

    @Override
    public Physician getPhysician(int physicianId) {
        try {
            SessionFactory factory = FactoryProvider.getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction hibernateTransaction = hibernateSession.beginTransaction();
            Physician physician = hibernateSession.get(Physician.class, physicianId);
            hibernateTransaction.commit();
            hibernateSession.close();
            return physician;
        } catch (Exception e) {
            throw new DataAccessException(
                    "Error getting Physician",
                    e
            );
        }
    }

    @Override
    public List<Physician> getPhysicians() {
        try {
            Session session = FactoryProvider.getSessionFactory().openSession();
            Query<Physician> query = session.createQuery("from Physician");
            List<Physician> listOfPhysicians = query.list();
            return listOfPhysicians;
        } catch (Exception e){
            throw new DataAccessException(
                    "Error getting a list of Physician",
                    e
            );
        }
    }
}
