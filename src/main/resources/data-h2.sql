
insert into admin_users
values('Mythili','ABC');

insert into type(id, name) values(0, 'BUS');
insert into type(id, name) values(1, 'TRAIN');
insert into type(id, name) values(2, 'BOTH');

insert into ROAD(name, length, speed, trafficstatus) values('ROAD1', 20.3, 20, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD2', 21.3, 40, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD3', 5.3, 40, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD4', 100.3, 70, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD5', 300.3, 50, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD6', 30.3, 45, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD7', 50.3, 30, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD8', 20.15, 40, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD9', 201.50, 40, 0);
insert into ROAD(name, length, speed, trafficstatus) values('ROAD10', 325.70, 40, 0);

insert into STOP(name, x, y, typeid) values('Stop1', 10, 20, 0);
insert into STOP(name, x, y, typeid) values('Stop2', 30, 40, 2);
insert into STOP(name, x, y, typeid) values('Stop3', 10, 40, 2);
insert into STOP(name, x, y, typeid) values('Stop4', 30, 70, 1);
insert into STOP(name, x, y, typeid) values('Stop5', 40, 50, 1);

insert into Route(name, No, typeid) values('Route1', 'B1', 0);
insert into Route(name, No, typeid) values('Route2', 'B2', 0);
insert into Route(name, No, typeid) values('South Route', 'T1', 1);


insert into Vehicle(Name, typeID) values('Bus1', 0);
insert into Vehicle(Name, typeID) values('Bus2', 0);
insert into Vehicle(Name, typeID) values('Train1', 1);


insert into STOPROAD(stopid_s, stopid_e, roadid) values(1, 2, 1);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(1, 2, 2);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(1, 2, 3);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(2, 3, 4);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(2, 3, 5);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(3, 4, 6);
insert into STOPROAD(stopid_s, stopid_e, roadid) values(4, 5, 7);

insert into ROUTESTOP(routeid, stopid) values(1, 1);
insert into ROUTESTOP(routeid, stopid) values(1, 2);
insert into ROUTESTOP(routeid, stopid) values(1, 3);
insert into ROUTESTOP(routeid, stopid) values(1, 4);
insert into ROUTESTOP(routeid, stopid) values(1, 5);

insert into ROUTESTOP(routeid, stopid) values(2, 1);
insert into ROUTESTOP(routeid, stopid) values(2, 2);
insert into ROUTESTOP(routeid, stopid) values(2, 5);
