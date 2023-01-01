import axios from 'axios';

export function usePostReview() {
  const postReview = (
    corporateId: string,
    internshipId: string,
    comment: string,
    rating: number
  ): Promise<PostReviewResponse> => {
    return new Promise((res, rej) => {
      axios
        .post<PostReviewResponse>(
          `/corporates/${corporateId}/internships/${internshipId}/reviews`,
          {
            reviewContent: comment,
            internshipId: internshipId,
            rating: rating,
          }
        )
        .then((response) => res(response.data));
    });
  };

  return { postReview };
}

export function usePostComment() {
  const postComment = (
    corporateId: string,
    internshipId: string,
    reviewId: string,
    parentType: string,
    comment: string,
    parentId: string
  ): Promise<PostCommentResponse> => {
    return new Promise((res, rej) => {
      axios
        .post<PostCommentResponse>(
          `/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`,
          {
            parentType: parentType,
            parentId: parentId,
            content: comment,
          }
        )
        .then((response) =>
          {
            console.log(response)
            res({
            ...response.data,
            content: comment,
          })}
        );
    });
  };
  return { postComment };
}
