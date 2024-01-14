import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';
import { HttpClient, HttpParams } from '@angular/common/http';
import { TopicsResponse } from '../interfaces/topicsResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  public getAll(userOnly: boolean): Observable<TopicsResponse> {
    
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userOnly", userOnly);

    return this.httpClient.get<TopicsResponse>(this.pathService, { params: queryParams });
  }
  
}
