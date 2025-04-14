import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SecteurActiviteService } from './Secteur-activite.service';

describe('SecteurActiviteService', () => {
  let service: SecteurActiviteService;
  let httpMock: HttpTestingController;

  const dummySecteurs = [
    { idSecteurActivite: 1, codeSecteurActivite: 'Code 1', libelleSecteurActivite: 'Secteur 1' },
    { idSecteurActivite: 2, codeSecteurActivite: 'Code 2', libelleSecteurActivite: 'Secteur 2' }
  ];

  const newSecteur = { idSecteurActivite: 3, codeSecteurActivite: 'Code 3', libelleSecteurActivite: 'Secteur 3' };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SecteurActiviteService]
    });
    service = TestBed.inject(SecteurActiviteService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();  // Ensures that no outstanding requests are left.
  });

  it('should fetch all secteurs', () => {
    service.getAllSecteurActivites().subscribe(secteurs => {
      const secteursArray = secteurs as any[]; // Type assertion: tell TypeScript this is an array
      expect(secteursArray.length).toBe(2);  // Test the length of the returned array
      expect(secteursArray).toEqual(dummySecteurs);  // Test that the array content matches the mock data
    });

    const req = httpMock.expectOne('http://localhost:8089/SpringMVC/secteurActivite/retrieve-all-secteurActivite');
    expect(req.request.method).toBe('GET');  // Test that the request method is GET
    req.flush(dummySecteurs);  // Simulate the response from the backend
  });

  it('should add a secteur', () => {
    service.addSecteurActivite(newSecteur).subscribe(response => {
      expect(response).toEqual(newSecteur);  // Ensure the response matches the newSecteur data
    });

    const req = httpMock.expectOne('http://localhost:8089/SpringMVC/secteurActivite/add-secteurActivite');
    expect(req.request.method).toBe('POST');  // Test that the request method is POST
    expect(req.request.body).toEqual(newSecteur);  // Ensure the request body contains the correct data
    req.flush(newSecteur);  // Simulate the response from the backend
  });

  it('should edit a secteur', () => {
    service.editSecteurActivite(newSecteur).subscribe(response => {
      expect(response).toEqual(newSecteur);  // Ensure the response matches the newSecteur data
    });

    const req = httpMock.expectOne('http://localhost:8089/SpringMVC/secteurActivite/modify-secteurActivite');
    expect(req.request.method).toBe('PUT');  // Test that the request method is PUT
    expect(req.request.body).toEqual(newSecteur);  // Ensure the request body contains the correct data
    req.flush(newSecteur);  // Simulate the response from the backend
  });

  it('should delete a secteur', () => {
    const id = 1;
    service.deleteSecteurActivite(id).subscribe(response => {
      expect(response).toEqual({});  // Empty response for DELETE
    });

    const req = httpMock.expectOne(`http://localhost:8089/SpringMVC/secteurActivite/remove-secteurActivite/${id}`);
    expect(req.request.method).toBe('DELETE');  // Test that the request method is DELETE
    req.flush({});  // Simulate the response from the backend (empty object for DELETE)
  });
});

