insert into admin_users(name, password) values('Mythili', 'AA');
insert into admin_users(name, password) values('Di', 'AB');
insert into admin_users(name, password) values('Xiaoyi', 'BA');
insert into admin_users(name, password) values('Bo', 'BB');

insert into busRoute(ID, No, Name) values(0, 10, 'Bus: UpTown');
insert into busRoute(ID, No, Name) values(1, 16, 'Bus: DownTown');

insert into trainRoute(ID, No, Name) values(2, 4, 'Train: North-South');
insert into trainRoute(ID, No, Name) values(3, 6, 'Train: East-West');


insert into bus(ID, routeID, currentstop, passengers, capacity, speed, direction) values(7,0,0,10,30,50,'INBOUND');
insert into bus(ID, routeID, currentstop, passengers, capacity, speed, direction) values(11,1,8,10,40,50,'INBOUND');

insert into train(ID, routeID, currentstop, passengers, capacity, speed, direction) values(17,2,1,10,120,50,'INBOUND');
insert into train(ID, routeID, currentstop, passengers, capacity, speed, direction) values(18,3,14,10,120,50,'INBOUND');


insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(0, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(1, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(2, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(3, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(4, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(5, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(6, 5, 10, 20, 10, 50, 0.7, 0);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(7, 5, 10, 20, 0, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(1, 5, 10, 20, 10, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(8, 5, 10, 20, 10, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(9, 5, 10, 20, 10, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(10, 5, 10, 20, 10, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(5, 5, 10, 20, 10, 50, 0.7, 1);
insert into busSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(11, 5, 10, 20, 0, 50, 0.7, 1);


insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(12, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(1, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(13, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(14, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(15, 5, 10, 20, 0, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(5, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(16, 5, 10, 20, 10, 50, 0.7, 2);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(9, 5, 10, 20, 10, 50, 0.7, 3);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(14, 5, 10, 20, 10, 150, 0.7, 3);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(3, 5, 10, 20, 10, 150, 0.7, 3);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(17, 5, 10, 20, 10, 50, 0.7, 3);
insert into trainSTOP(name, riders, x, y, length, speed, trafficstatus, routeid) values(18, 5, 10, 20, 0, 50, 0.7, 3);



