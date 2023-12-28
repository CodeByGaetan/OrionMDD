import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostRequest } from 'src/app/interfaces/requests/postRequest.interface';
import { Topic } from 'src/app/interfaces/topic.interface';
import { PostService } from 'src/app/services/post.service';
import { TopicService } from 'src/app/services/topic.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {

  public topics$!: Observable<Topic[]>;

  public postForm = this.formBuilder.group({
    topicId: [
      0,
      [Validators.required]
    ],
    title: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(20)
      ]
    ],
    content: [
      '',
      [
        Validators.required,
        Validators.min(8)
      ]
    ]
  });

  public onError = false;

  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService,
    private topicService: TopicService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.topics$ = this.topicService.getAll();
  }

  createPost(): void {
    const postRequest = this.postForm.value as PostRequest;
    this.postService.createPost(postRequest).subscribe({
      next: (_: void) => {
        this.router.navigateByUrl('/posts');
      },
      error: () => this.onError = true
    });
  }

}
