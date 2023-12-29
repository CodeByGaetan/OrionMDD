import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }
}
