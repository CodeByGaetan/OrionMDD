import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Post } from '../../interfaces/post.interface';
import { PostService } from '../../services/post.service';
import { Comment } from '../../interfaces/comment.interface';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { CommentRequest } from 'src/app/interfaces/requests/commentRequest.interface';

@Component({
  selector: 'app-detail-post',
  templateUrl: './detail-post.component.html',
  styleUrls: ['./detail-post.component.scss']
})
export class DetailPostComponent implements OnInit {

  postId!: number;
  post$!: Observable<Post>;
  comments$!: Observable<Comment[]>;
  commentForm = this.formBuilder.group({
    content: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(255)
      ]
    ]
  });
  @ViewChild('commentNgForm') commentNgForm!: NgForm;
  onError = false;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.postId = +this.route.snapshot.params['id'];
    this.fetchPost();
  }

  fetchPost(): void {
    this.post$ = this.postService.getById(this.postId);
    this.comments$ = this.postService.getComments(this.postId);
  }
  
  commentPost(): void {
    const commentRequest = this.commentForm.value as CommentRequest;
    this.postService.addComment(this.postId, commentRequest).subscribe({
      next: (_: void) => {
        this.fetchPost();
        this.commentNgForm.resetForm();
      },
      error: () => this.onError = true
    });
  }
  



}
