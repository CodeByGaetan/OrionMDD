import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { }

  public getTopicIds(): Observable<number[]> {
    return this.httpClient.get<number[]>(`${this.pathService}/topics`);
  }

  public subscribeTopic(topicId: number): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/topics/${topicId}`, null)
  }

  public unSubscribeTopic(topicId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/topics/${topicId}`)
  }
}
