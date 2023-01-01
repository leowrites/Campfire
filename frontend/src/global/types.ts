interface User {
  username: string;
  name: string;
  email: string;
  connectedUsers: User[];
  incomingConnectionRequests: User[];
  outgoingConnectionRequests: User[];
}

interface TextFieldOnChange {
  (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>): void;
}

interface TextInputEvent extends React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement> {}

interface CompanyDetails {
  companyName: string;
  companyInfo: string;
}

interface InternshipDetails {
  id: string
  jobTitle: string
  companyId: string;
  reviews: Review[]
  creator: String
}

interface Review {
  id: string
  user: User
  datePosted: string
  review: string
  numLikes: number
  numDislikes: number
  comments: Comment[]
  rating: number
  content: string
}

interface Comment {
  user: User
  content: string
  datePosted: string
  comments: Comment[]
  parentId: string
  id: string
}

interface PostCommentResponse {
  status: string;
  message: string;
  id: string;
  datePosted: string;
  content: string
}

interface PostReviewResponse {
  status: string;
  message: string;
}

enum ServerResponseStatus {
  SUCCESS = 'success',
  ERROR = 'error',
  WARNING = 'warning',
}

enum ConnectionStatus {
  OUTGOING_CONNECTION_REQUEST = 'OUTGOING_CONNECTION_REQUEST',
  INCOMING_CONNECTION_REQUEST = 'INCOMING_CONNECTION_REQUEST',
}
