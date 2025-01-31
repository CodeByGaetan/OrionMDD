import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { UserRequest } from '../interfaces/userRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public getUserInfo(): Observable<User> {
    return this.httpClient.get<User>(this.pathService);
  }

  public subscribeTopic(topicId: number): Observable<number[]> {
    return this.httpClient.post<number[]>(`${this.pathService}/topics/${topicId}`, null);
  }

  public unSubscribeTopic(topicId: number): Observable<number[]> {
    return this.httpClient.delete<number[]>(`${this.pathService}/topics/${topicId}`);
  }

}
