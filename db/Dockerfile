# Use standard official MySQL image
FROM mysql:latest

WORKDIR /tmp

# Copy all dump files
COPY /test_db/*.sql /tmp/
COPY /test_db/*.dump /tmp/

# Copy main SQL script to init directory
COPY /test_db/employees.sql /docker-entrypoint-initdb.d/

# Set environment variables for authentication
ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_ROOT_HOST=%