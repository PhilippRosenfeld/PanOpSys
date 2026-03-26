import { Component, OnInit, OnDestroy, NgZone, ChangeDetectorRef } from '@angular/core';
import { CommonModule, JsonPipe } from '@angular/common';
import { Subscription, timer, switchMap } from 'rxjs';
import { AtmoService } from '../../service/atmo.service';

@Component({
  selector: 'app-atmo',
  standalone: true,
  imports: [CommonModule, JsonPipe],
  templateUrl: './atmo-component.html'
})
export class AtmoComponent implements OnInit, OnDestroy {
  atmo: { temp: number, pressure: number, humidity: number } | null = null;
  timeInfo: string | null = null;
  test: string = 'funktioniert';
  private subscription: Subscription = new Subscription();

  constructor(
    private atmoService: AtmoService,
    private zone: NgZone,
    private cdr: ChangeDetectorRef
) {}

  ngOnInit(): void {
    this.subscription = timer(0, 3000).pipe(
      switchMap(() => this.atmoService.getLatest())
    ).subscribe({
      next: (data) => {
        this.zone.run(() => {
          this.atmo = data;
          this.timeInfo = data.timestamp
            ? new Date(data.timestamp).toLocaleString()
            : '-';
          this.cdr.detectChanges();
        });
      },
      error: (err) => console.error(err)
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
