package one.digitalinnovation.personapi.services;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static one.digitalinnovation.personapi.utils.PersonUtils.createfakeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSucessSavedMessage() {

		PersonDTO personDTO = createfakeDTO();
		Person expectedSavedPerson = createFakeEntity();
		 
	    when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());

		MessageResponseDTO sucessMessage = personService.createPerson(personDTO);

		assertEquals(expectedSavedPerson, sucessMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with  " + id)
				.build();
	}
}
