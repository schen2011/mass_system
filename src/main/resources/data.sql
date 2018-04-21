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
-----------------------------------------------------------------------------------------------------------------------
insert into Route(RouteID, RouteNumber, RouteName, TYPE) values(1, 1, 'route1', 1);
insert into Route(RouteID, RouteNumber, RouteName, TYPE) values(2, 2, 'route2', 1);
insert into Route(RouteID, RouteNumber, RouteName, TYPE) values(100, 100,'North-South', 2);

insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 1, 1);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 2, 2);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 3, 3);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 4, 4);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 5, 5);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(1, 7, 6);

insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(100, 1, 1);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(100, 6, 2);
insert into ROUTESTOP(ROUTEID, STOPID, Sequence) values(100, 7, 3);

insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(1, 'stop1', 1000, 1, 1, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(2, 'stop2', 500, 2, 2, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(1, 'stop1', 1000, 1, 1, 2);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(3, 'stop3', 1500, 3, 3, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(4, 'stop4', 1000, 1, 1, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(5, 'stop5', 500, 2, 2, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(6, 'stop6', 1000, 1, 1, 2);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(7, 'stop7', 1500, 3, 3, 1);
insert into STOP(STOPID, STOPNAME, RIDERS, X, Y, TYPE) values(7, 'stop7', 1500, 3, 3, 2);

insert into VEHICLE(VehicleID, routeID, currentstop, nextstop, passengers, capacity, speed, direction, type) values(1,1,1,2,10,30,25,'INBOUND', 1);
insert into VEHICLE(VehicleID, routeID, currentstop, nextstop, passengers, capacity, speed, direction, type) values(100,100,1,6,10,120,30,'INBOUND', 2);

insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(1,'ROAD 1',10000.50,30,1,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(2,'ROAD 2',10000.50,30,2,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(3,'ROAD 3',234.50,30,1,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(4,'ROAD 4',551.50,30,2,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(5,'ROAD 5',51123.50,30,1,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(6,'ROAD 6',55.50,30,2,1);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(7,'RAIL 1',8000,30,0,2);
insert into ROAD(ROADID, ROADNAME, ROADLENGTH, SPEEDLIMIT, TRAFFICSTATUS, TYPE) values(8,'RAIL 2',129909.50,30,0,2);


insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(1,'Rider1',1,4,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(2,'Rider2',1,2,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(3,'Rider3',1,3,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(4,'Rider4',1,4,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(5,'Rider5',2,5,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(6,'Rider6',2,6,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(7,'Rider7',1,7,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(8,'Rider8',1,2,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(9,'Rider9',1,2,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(10,'Rider10',1,2,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(11,'Rider11',1,7,100);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(12,'Rider12',1,7,100);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(13,'Rider13',1,6,100);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(14,'Rider14',1,3,1);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(15,'Rider15',1,7,100);
insert into Rider(RiderID, RiderNAME, CurrentStopID, DestinationStopID, RouteID) values(16,'Rider16',1,7,100);

insert into Event(rank, VEHICLEID) values(1, 1);
insert into Event(rank, VEHICLEID) values(1, 1);
insert into Event(rank, VEHICLEID) values(1, 1);
insert into Event(rank, VEHICLEID) values(1, 1);
insert into Event(rank, VEHICLEID) values(1, 100);
insert into Event(rank, VEHICLEID) values(1, 100);
insert into Event(rank, VEHICLEID) values(1, 1);

insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 1, 2, 1);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 1, 2, 2);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 2, 1, 2);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 2, 1, 1);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 2, 3, 3);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 3, 2, 3);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 3, 4, 4);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 4, 3, 4);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 4, 5, 5);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 5, 4, 5);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 5, 7, 6);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (1, 7, 5, 6);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (100, 1, 6, 7);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (100, 6, 1, 7);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (100, 6, 7, 8);
insert into STOPROAD(RouteID, stopid_from, stopid_to, roadid) values (100, 7, 6, 8);




