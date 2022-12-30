interface User {
    username: string;
    name: string;
    email: string;
    connectedUsers: string[];
    incomingConnectionRequests: string[];
    outqoingConnectionRequests: string[];
}

enum ConnectionStatus {
    OUTGOING_CONNECTION_REQUEST = 'OUTGOING_CONNECTION_REQUEST',
    INCOMING_CONNECTION_REQUEST = 'INCOMING_CONNECTION_REQUEST',
}