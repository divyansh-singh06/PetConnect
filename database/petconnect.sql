-- =========================================================
-- PetConnect Database Setup
-- =========================================================
-- This script creates the database structure and safe demo data.
-- No real user credentials or personal information are included.
-- =========================================================

CREATE DATABASE IF NOT EXISTS petconnect;
USE petconnect;


-- =========================================================
-- USERS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);


-- =========================================================
-- PETS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS pets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    breed VARCHAR(100),
    age INT NOT NULL,
    adopted BOOLEAN DEFAULT FALSE,
    owner_id INT,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);


-- =========================================================
-- ADOPTIONS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS adoptions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    pet_id INT,
    adoption_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (pet_id) REFERENCES pets(id)
);


-- =========================================================
-- ADOPTION REQUESTS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS adoption_requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pet_id INT NOT NULL,
    adopter_id INT NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pet_id) REFERENCES pets(id),
    FOREIGN KEY (adopter_id) REFERENCES users(id)
);


-- =========================================================
-- WISHLIST TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS wishlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    pet_id INT NOT NULL,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (pet_id) REFERENCES pets(id),
    UNIQUE (user_id, pet_id)
);


-- =========================================================
-- SAFE DEMO USERS
-- =========================================================

INSERT INTO users (name, email, password, address)
VALUES
('Demo Owner', 'owner@petconnect.demo', 'demo123', 'PetConnect Demo City'),
('Demo Adopter', 'adopter@petconnect.demo', 'demo123', 'PetConnect Demo City');


-- =========================================================
-- SAFE DEMO PETS
-- =========================================================

INSERT INTO pets (name, type, breed, age, adopted, owner_id)
VALUES
('Buddy', 'Dog', 'Golden Retriever', 3, FALSE, 1),
('Milo', 'Cat', 'Persian', 2, FALSE, 1),
('Coco', 'Dog', 'Beagle', 4, FALSE, 1);


-- =========================================================
-- END OF DATABASE SETUP
-- =========================================================