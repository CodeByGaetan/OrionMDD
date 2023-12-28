import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../interfaces/post.interface';
import { Observable } from 'rxjs';
import { Comment } from '../interfaces/comment.interface';
import { CommentRequest } from '../interfaces/requests/commentRequest.interface';
import { PostRequest } from '../interfaces/requests/postRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  
  private pathService = 'api/posts';

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public getById(id: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public getComments(id: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${this.pathService}/${id}/comments`)
  }

  public addComment(id: number, commentRequest: CommentRequest): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/comments`, commentRequest)
  }

  public createPost(postRequest: PostRequest): Observable<void> {
    return this.httpClient.post<void>(this.pathService, postRequest);
  }


}
