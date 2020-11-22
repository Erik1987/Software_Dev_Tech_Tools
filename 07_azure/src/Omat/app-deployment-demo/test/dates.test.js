let { createEventDateFilter, eventDateComparator } = require('../events/dates');
let assert = require('assert');

const january1Event = { "event_dates": { "starting_day": '2022-01-01T12:00:00Z' } }
const christmasEvent = { "event_dates": { "starting_day": '2022-12-24T12:00:00Z' } }
const unknownEvent = { "event_dates": { "starting_day": null } }

suite('Event date filter');

test('accepts all events with dates by default', () => {
    let allEvents = [january1Event, christmasEvent, unknownEvent];

    let filtered = allEvents.filter(createEventDateFilter());

    assert.deepStrictEqual(filtered, [january1Event, christmasEvent]);
})

test('filters events by minimum date', () => {
    let allEvents = [january1Event, christmasEvent, unknownEvent];

    let filtered = allEvents.filter(createEventDateFilter('2022-06-01T12:00:00Z'));

    assert.deepStrictEqual(filtered, [christmasEvent]);
});

test('filters events by maximum date', () => {
    let allEvents = [january1Event, christmasEvent, unknownEvent];

    let filtered = allEvents.filter(createEventDateFilter(undefined, '2022-06-01T12:00:00Z'));

    assert.deepStrictEqual(filtered, [january1Event]);
});

suite('Event date comparator');

test('compares two dates correctly', () => {
    let correctOrder = eventDateComparator(january1Event, christmasEvent);
    assert(correctOrder < 0);

    let incorrectOrder = eventDateComparator(christmasEvent, january1Event);
    assert(incorrectOrder > 0);

    let equalOrder = eventDateComparator(christmasEvent, christmasEvent);
    assert(equalOrder === 0);
});

test('compares events with no dates before all others', () => {
    let correctOrder = eventDateComparator(unknownEvent, christmasEvent);
    assert(correctOrder < 0);

    let incorrectOrder = eventDateComparator(january1Event, unknownEvent);
    assert(incorrectOrder > 0);

    let equalOrder = eventDateComparator(unknownEvent, unknownEvent);
    assert(equalOrder === 0);
});

test('sorts list of events into correct order', () => {
    let events = [christmasEvent, unknownEvent, january1Event];

    events.sort(eventDateComparator);

    assert.deepStrictEqual(events, [unknownEvent, january1Event, christmasEvent]);
});