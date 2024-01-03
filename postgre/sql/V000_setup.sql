CREATE ROLE "dopad" WITH SUPERUSER;
CREATE USER "dopad-ro" WITH PASSWORD 'dopad-ro' IN ROLE "dopad";
CREATE USER "dopad-dml" WITH PASSWORD 'dopad-dml' IN ROLE "dopad";
ALTER USER "dopad-app" SET search_path = public,dopad_platform;