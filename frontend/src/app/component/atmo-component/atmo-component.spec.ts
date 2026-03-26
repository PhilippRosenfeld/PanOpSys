import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtmoComponent } from './atmo-component';

describe('AtmoComponent', () => {
  let component: AtmoComponent;
  let fixture: ComponentFixture<AtmoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AtmoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AtmoComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
