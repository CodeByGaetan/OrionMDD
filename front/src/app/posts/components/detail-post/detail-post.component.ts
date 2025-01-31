import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, ignoreElements, tap, throwError } from 'rxjs';
import { Post } from '../../interfaces/post.interface';
import { PostService } from '../../services/post.service';
import { Comment } from '../../interfaces/comment.interface';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { CommentRequest } from 'src/app/posts/interfaces/commentRequest.interface';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-detail-post',
  templateUrl: './detail-post.component.html',
  styleUrls: ['./detail-post.component.scss']
})
export class DetailPostComponent implements OnInit {

  private postId!: number;
  public post$!: Observable<Post>;
  public comments$!: Observable<Comment[]>;
  public commentForm = this.formBuilder.group({
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

  public onError = false;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar
  ) { }

  private fetchPost(): void {
    this.post$ = this.postService.getById(this.postId).pipe(
      catchError(err => {
        this.router.navigateByUrl('/not-found')
        return throwError(() => err);
      })
    );
  }

  private fetchComments(): void {
    this.comments$ = this.postService.getComments(this.postId).pipe(
      catchError(err => {
        this.onError = true;
        return throwError(() => err);
      })
    );
  }

  public ngOnInit(): void {
    this.postId = +this.route.snapshot.params['id'];
    this.fetchPost();
    this.fetchComments();
  }

  public commentPost(): void {
    const commentRequest = this.commentForm.value as CommentRequest;
    this.postService.addComment(this.postId, commentRequest).subscribe({
      next: (_: void) => {
        this.fetchComments();
        this.commentNgForm.resetForm();
      },
      error: () => this.snackBar.open("Erreur lors de l'ajout du commentaire", "Fermer", {
        panelClass: 'error-snackbar'
      })
    });
  }

}