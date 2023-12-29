import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { PostService } from '../../services/post.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.scss']
})
export class ListPostComponent implements OnInit {

  public posts$!: Observable<Post[]>;

  constructor(
    private postService: PostService,
    private router: Router
  ) { }

  public ngOnInit(): void {
    this.posts$ = this.postService.getAll();
  }

  public goPost(postId: number): void {
    this.router.navigateByUrl(`/posts/${postId}`);
  }

}
