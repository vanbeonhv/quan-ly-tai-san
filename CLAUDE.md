# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Vietnamese asset management system ("quan-ly-tai-san") built with Java and MySQL. It implements a client-server architecture using UDP communication for asset management operations.

## Build and Development Commands

This is a NetBeans project using Apache Ant for building:

```bash
# Build the project
ant

# Clean build artifacts
ant clean

# Run the server
ant run -Dmain.class=Run.ServerRun

# Run the client (default)
ant run
```

The project requires MySQL Connector J 9.3.0 which is referenced in the classpath.

## Architecture

### Core Components

- **Model Layer**: `TaiSan` (Asset entity) and `Event` (communication wrapper)
- **Controller Layer**: Separate controllers for client and server UDP communication
- **View Layer**: Swing-based GUI (`JFTaiSan`) for the client interface
- **Run Layer**: Entry points for server (`ServerRun`) and client (`ClientRun`)

### Client-Server Architecture

- **Server** (`ServerController`): UDP server on port 5555, connects to MySQL database "quan-ly-tai-san"
- **Client** (`ClientController`): UDP client on port 6666, communicates with server via serialized `Event` objects
- **Database**: MySQL with default connection (root user, no password, localhost:3306)

### Key Classes

- `TaiSan`: Asset model with fields: maTaiSan (ID), tenTaiSan (name), loaiTaiSan (type), viTriPhong (room location), giaTri (value)
- `Event`: Serializable wrapper for client-server communication containing action and TaiSan object
- `ServerController`: Handles UDP communication and database operations
- `ClientController`: Manages client-side UDP communication with server
- `JFTaiSan`: Swing GUI for asset management interface

## Database Requirements

- MySQL server running on localhost:3306
- Database name: "quan-ly-tai-san"
- Default credentials: root user with empty password
- MySQL Connector J 9.3.0 must be available in classpath

## Development Notes

- Java 1.8 compatibility (source and target)
- Uses UDP DatagramSocket for network communication
- All model objects implement Serializable for network transmission
- Client includes sample asset data for testing
- NetBeans project structure with nbproject configuration files