const assert = require('assert');
let { getEventsAsync } = require('../events/client');

suite('fetch events from Helsinki API');

test('getEvents returns an array of event objects', async () => {
    let events = await getEventsAsync();

    assert(events.length > 100);
});