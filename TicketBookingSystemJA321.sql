CREATE DATABASE TicketBookingSystem;

USE TicketBookingSystem;

CREATE TABLE Venue (venue_id INT PRIMARY KEY,venue_name VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL);

CREATE TABLE Booking (
    booking_id INT PRIMARY KEY,
    customer_id INT,event_id INT,   
    num_tickets INT NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL,
    booking_date DATE NOT NULL);
    
    CREATE TABLE Customer (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(100) NOT NULL,
    booking_id INT,
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id));
    
    CREATE TABLE Event (
    event_id INT PRIMARY KEY,
    event_name VARCHAR(50) NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    venue_id INT,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    ticket_price DECIMAL(10, 2) NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    booking_id INT,
    FOREIGN KEY (venue_id) REFERENCES Venue(venue_id),
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id));


INSERT INTO Venue (venue_id, venue_name, address) VALUES 
(1, 'Grand Hall', '123 Main St, New York'),
(2, 'City Stadium', '456 Sports Ave, Los Angeles'),
(3, 'Cineplex Cinema', '789 Movie Blvd, Chicago'),
(4, 'Theater Royale', '101 Stage Dr, Houston'),
(5, 'Open Arena', '202 Open Park, Miami'),
(6, 'Music Hall', '303 Concert St, Atlanta'),
(7, 'Sports Complex', '404 Stadium Rd, Boston'),
(8, 'Film House', '505 Cinema Ln, Dallas'),
(9, 'Concert Dome', '606 Melody Dr, San Francisco'),
(10, 'Arena Central', '707 Play St, Washington');

INSERT INTO Event (event_id, event_name, event_date, event_time, venue_id, total_seats, 
available_seats, ticket_price, event_type, booking_id) VALUES 
(1, 'Movie Night', '2024-10-01', '20:00:00', 3, 200, 150, 10.50, 'Movie', 1),
(2, 'Football Match', '2024-10-02', '13:00:00', 2, 50000, 47000, 50.00, 'Sports', 2),
(3, 'Rock Concert', '2024-10-03', '21:00:00', 6, 30000, 29000, 100.00, 'Concert', 3),
(4, 'Drama Play', '2024-10-04', '18:00:00', 4, 1000, 800, 30.00, 'Movie', 4),
(5, 'Basketball Game', '2024-10-05', '14:00:00', 7, 20000, 19500, 60.00, 'Sports', 5),
(6, 'Jazz Festival', '2024-10-06', '21:00:00', 5, 5000, 4500, 70.00, 'Concert', 6),
(7, 'Comedy Show', '2024-10-07', '20:00:00', 4, 1000, 900, 25.00, 'Movie', 7),
(8, 'Soccer Final', '2024-10-08', '17:00:00', 2, 50000, 48000, 75.00, 'Sports', 8),
(9, 'Film Premiere', '2024-10-09', '18:00:00', 3, 200, 180, 15.00, 'Movie', 9),
(10, 'Pop Concert', '2024-10-10', '20:00:00', 9, 40000, 39000, 120.00, 'Concert', 10);

INSERT INTO Customer (customer_id, customer_name, email, phone_number, booking_id) VALUES 
(1, 'John Doe', 'johndoe@gmail.com', '123-456-7890', 1),
(2, 'Jane Smith', 'janesmith@yahoo.com', '234-567-8901', 2),
(3, 'Mike Johnson', 'mikej@gmail.com', '345-678-9012', 3),
(4, 'Emily Davis', 'emilyd@yahoo.com', '456-789-0123', 4),
(5, 'Chris Lee', 'chrisl@gmail.com', '567-890-1234', 5),
(6, 'Sophia Brown', 'sophiab@yahoo.com', '678-901-2345', 6),
(7, 'David Miller', 'davidm@gmail.com', '789-012-3456', 7),
(8, 'Isabella Wilson', 'isabellaw@yahoo.com', '890-123-4567', 8),
(9, 'James Garcia', 'jamesg@gmail.com', '901-234-5678', 9),
(10, 'Olivia Martinez', 'oliviam@yahoo.com', '012-345-6789', 10);

INSERT INTO Booking (booking_id, customer_id, event_id, num_tickets,
total_cost, booking_date) VALUES
(1, 1, 1, 2, 111500.00, '2024-09-01'),
(2, 2, 2, 5, 112500.00, '2024-09-02'),
(3, 3, 3, 3, 41500.00, '2024-09-03'),
(4, 4, 4, 1, 11100.00, '2024-09-04'),
(5, 5, 5, 4, 80000.00, '2024-09-05'),
(6, 6, 6, 2, 35000.00, '2024-09-06'),
(7, 7, 7, 1, 10000.00, '2024-09-07'),
(8, 8, 8, 6, 180000.00, '2024-09-08'),
(9, 9, 9, 2, 160000.00, '2024-09-09'),
(10, 10, 10, 3, 200000.00, '2024-09-10');
SELECT * from venue;
SELECT * from event;
SELECT * from customer;
SELECT * from booking;

#task 2

SELECT * from event;

SELECT * from event WHERE available_seats > 0;

SELECT * from event WHERE event_name LIKE '%cup%';

SELECT * from event WHERE ticket_price BETWEEN 1000 AND 2500;

SELECT * from event WHERE event_date BETWEEN '2024-05-03' AND '2024-11-07';

SELECT * from event WHERE available_seats > 0 AND event_type = 'Concert';

SELECT * from customer  LIMIT 5 OFFSET 5;

SELECT * from booking WHERE num_tickets > 4;

SELECT * FROM customer WHERE phone_number LIKE '%000';

SELECT * from event WHERE total_seats > 15000;

SELECT * from event WHERE event_name NOT LIKE 'x%' AND event_name
NOT LIKE 'y%' AND event_name NOT LIKE'%z';

#task 3

SELECT event_type, AVG(ticket_price) AS average_price 
FROM event GROUP BY event_type;

SELECT e.event_name, SUM(b.total_cost) AS total_revenue 
FROM booking b JOIN event e ON b.event_id = e.event_id 
GROUP BY e.event_name;

SELECT e.event_name, SUM(b.num_tickets) AS total_tickets_sold 
FROM booking b JOIN event e ON b.event_id = e.event_id 
GROUP BY e.event_name;

SELECT e.event_name FROM event e LEFT JOIN booking b ON e.event_id = b.event_id 
WHERE b.booking_id IS NULL; 

SELECT c.customer_name, SUM(b.num_tickets) AS total_tickets 
FROM booking b JOIN customer c ON b.customer_id = c.customer_id GROUP BY c.customer_name 
ORDER BY total_tickets DESC LIMIT 1;

SELECT e.event_name, MONTH(b.booking_date) AS month, SUM(b.num_tickets) AS total_tickets_sold
FROM booking b JOIN event e ON b.event_id = e.event_id GROUP BY
e.event_name, MONTH(b.booking_date) ORDER BY month;

SELECT v.venue_name, AVG(e.ticket_price) AS average_ticket_price 
FROM event e JOIN venue v ON e.venue_id = v.venue_id GROUP BY v.venue_name;

SELECT e.event_type, SUM(b.num_tickets) AS total_tickets_sold 
FROM booking b JOIN event e ON b.event_id = e.event_id  GROUP BY e.event_type;

SELECT YEAR(E.event_date) AS event_year,
SUM((E.total_seats - E.available_seats) * E.ticket_price) AS total_revenue
FROM Event E JOIN Booking B ON E.booking_id = B.booking_id
GROUP BY YEAR(E.event_date) ORDER BY YEAR(E.event_date);  

SELECT c.customer_name, COUNT(DISTINCT b.event_id) AS event_count 
FROM booking b JOIN customer c ON b.customer_id = c.customer_id 
GROUP BY c.customer_name  HAVING event_count > 1;


SELECT c.customer_name, SUM(b.total_cost) AS total_revenue 
FROM booking b JOIN customer c ON b.customer_id = c.customer_id 
GROUP BY c.customer_name;

SELECT e.event_type, v.venue_name, AVG(e.ticket_price) AS average_ticket_price 
FROM event e JOIN venue v ON e.venue_id = v.venue_id 
GROUP BY e.event_type, v.venue_name;

SELECT c.customer_name, SUM(b.num_tickets) AS total_tickets 
FROM booking b JOIN customer c ON b.customer_id = c.customer_id  
WHERE b.booking_date >= CURDATE() - INTERVAL 30 DAY 
GROUP BY c.customer_name;

#task 4

SELECT v.venue_name, (select AVG(e.ticket_price) 
FROM event e 
WHERE e.venue_id = v.venue_id) AS avg_ticket_price 
FROM venue v; 

SELECT event_name FROM event e WHERE available_seats <
(SELECT total_seats /2 FROM event WHERE event_id = e.event_id);

SELECT e.event_name,(SELECT SUM(b.num_tickets) 
FROM booking b WHERE b.event_id = e.event_id) AS total_tickets_sold 
FROM event e;


SELECT customer_name 
FROM customer c 
WHERE NOT EXISTS (SELECT 1 FROM booking b WHERE b.customer_id = c.customer_id);

SELECT event_name 
FROM event 
WHERE event_id NOT IN (SELECT distinct event_id FROM booking);

SELECT event_type, SUM(total_tickets) AS total_tickets_sold
FROM (SELECT e.event_type, b.num_tickets AS total_tickets
FROM booking b 
JOIN event e ON b.event_id = e.event_id) AS event_ticket_sales GROUP BY event_type;

SELECT event_name,ticket_price 
FROM event 
WHERE ticket_price>(SELECT AVG(ticket_price)FROM event);

SELECT c.customer_name, (SELECT SUM(b.total_cost) 
FROM booking b
WHERE b.customer_id = c.customer_id) AS total_revenue 
FROM customer c;

SELECT customer_name
FROM customer c
WHERE customer_id IN(SELECT b.customer_id FROM booking b JOIN event e ON b.event_id = e.event_id WHERE e.venue_id = (SELECT venue_id 
FROM venue
WHERE venue_name = 'Music Hall'));

SELECT event_type,(SELECT SUM(b.num_tickets) 
FROM booking b
JOIN event e2 ON b.event_id = e2.event_id WHERE e2.event_type = e.event_type) AS total_tickets_sold 
FROM event e
GROUP BY event_type;

SELECT c.customer_name,(SELECT COUNT(b.booking_id) 
FROM booking b 
WHERE b.customer_id = c.customer_id 
AND DATE_FORMAT(b.booking_date,'%Y-"m') = '2024-12') AS bookings_in_month 
FROM customer c;

SELECT v.venue_name,(SELECT AVG(ticket_price) 
FROM event e 
WHERE e.venue_id = v.venue_id) AS avg_ticket_price 
FROM venue v;




























