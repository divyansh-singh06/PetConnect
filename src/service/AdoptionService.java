package service;

import dao.AdoptionDAO;

public class AdoptionService {

    private AdoptionDAO adoptionDAO;

    public AdoptionService() {
        adoptionDAO = new AdoptionDAO();
    }

    // synchronized prevents two threads from processing
    // an adoption operation at the same time.
    public synchronized boolean recordAdoption(
            int userId,
            int petId) {

        return adoptionDAO.addAdoption(userId, petId);
    }
}